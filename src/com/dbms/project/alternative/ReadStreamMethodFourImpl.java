package com.dbms.project.alternative;


import com.dbms.project.streamInterfaces.ReadStreamInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadStreamMethodFourImpl implements ReadStreamInterface {
    RandomAccessFile file;
    FileChannel channel;
    int B;
    MappedByteBuffer mpbuf;

    Boolean endOfStream = false;

    int cursor; // Current position

    public ReadStreamMethodFourImpl(int B) {
        this.file = null;
        this.channel = null;
        this.B = B;
        this.mpbuf = null;
    }

    //A file channel is created that is connected to the file needing to be read, creating a random-access file with a read mode
    //When the position of the channel is changed by reading for example, the file pointer in the corresponding file is also changed
    @Override
    public void open(String path) throws FileNotFoundException {
        file = new RandomAccessFile(path,"r");
        this.channel = file.getChannel();
    }

    //Read is performed by mapping B characters of the file into internal memory through memory mapping
    //Whenever you need to read outside of the mapped portion, the next B element portion of the file is mapped.
    //mapping B characters of the file directly into memory results in a buffer with these characters (B is in bytes but +/- corresponds to number of characters)
    @Override
    public String readLn() throws IOException {
        //all the read characters are being added to currentString until \n is read
        String currentString = "";
        int endLine = '\n';

        //mapping B characters of the file directly into memory
        if (mpbuf == null) {mpbuf = channel.map(FileChannel.MapMode.READ_ONLY, channel.position(),B);}
        Boolean keepReading = true;
        Boolean lastBuf = false;

        int newpos = 0;
        cursor =-1;
        while(keepReading) {
            while((mpbuf.hasRemaining()) && ((cursor = mpbuf.get()) != endLine)){
                currentString = currentString + (char)cursor;
                newpos += 1; }
            //keep reading mapped characters and map characters until an end of line symbol is found
            if (cursor == endLine) {
                keepReading = false;
                channel.position((int)channel.position() + newpos+1 );
                //keep reading until no more characters are left and the end of the file is reached, denoted by the last buffer boolean
            } else if (lastBuf) {
                keepReading = false;
                endOfStream = true;
                channel.close();

            } else {
                //keep reading is true so next B characters of the file are mapped directly into memory
                channel.position((int)channel.position() + newpos);
                newpos = 0;
                //we check if the file still has characters left to be mapped next
                int left = (int)channel.size() - (int)channel.position();
                int length = B;
                if (left < B){
                    //if no B characters can be mapped anymore, the number of available characters is mapped
                    length = left;
                    lastBuf = true;}
                //the other buffer has been used up so we use this memory space for the next characters
                mpbuf = channel.map(FileChannel.MapMode.READ_ONLY, channel.position(),length);
            }
        }
        //System.out.println(currentString);
        return currentString;

    }

    //Because we used a random access file, the seek operation can be used directly, however the last buffer with
    //its characters might not be correct anymore so it should be refilled
    @Override
    public void seek(int pos) throws IOException {
        file.seek(pos);
        mpbuf = null;
    }
    //returning a boolean: true if the end of the stream was reached, and false otherwise
    @Override
    public boolean endOfStream() {
        return endOfStream;
    }
}
