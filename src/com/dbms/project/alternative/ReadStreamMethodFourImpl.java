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
    int bufPos;

    public ReadStreamMethodFourImpl(int B) {
        this.file = null;
        this.channel = null;
        this.B = B;
        this.mpbuf = null;
        this.bufPos = 0;
    }

    @Override
    public void open(String path) throws FileNotFoundException {
        file = new RandomAccessFile(path,"r");
        this.channel = file.getChannel();
    }

    @Override
    public void readLn() throws IOException {
        List<Character> charList = new ArrayList<>();
        int endLine = '\n';
        if (mpbuf == null) {mpbuf = channel.map(FileChannel.MapMode.READ_ONLY, channel.position(),B);}
        Boolean keepReading = true;
        Boolean lastBuf = false;
        int skip = 0;
        if (bufPos>0){
            bufPos--;
            while (mpbuf.capacity()<= mpbuf.position()+bufPos)
        {
        skip += (mpbuf.capacity() - mpbuf.position());
        bufPos -= (mpbuf.capacity() - mpbuf.position());
        mpbuf.position(0);}}
        int newpos = bufPos;
        cursor =-1;
        while(keepReading) {
            if (skip == 0){
                if(bufPos>0) {
                    mpbuf.position(mpbuf.position() + (bufPos));
                    newpos=bufPos;
                    bufPos = 0; }
                while((mpbuf.hasRemaining()) && ((cursor = mpbuf.get()) != endLine)){
                    char ch = (char)cursor;
                    charList.add(ch);
                    newpos += 1; }
            }

            if (cursor == endLine) {
                keepReading = false;
                channel.position((int)channel.position() + newpos+1 );
            } else if (lastBuf) {
                keepReading = false;
                endOfStream = true;
                channel.close();

            } else {
                if(skip>0) {
                    channel.position((int)channel.position() + skip);
                    skip = 0;
                }else{channel.position((int)channel.position() + newpos);}

                newpos = 0;
                int left = (int)channel.size() - (int)channel.position();
                int length = B;
                if (left < B){
                    length = left;
                    lastBuf = true;}
                mpbuf = channel.map(FileChannel.MapMode.READ_ONLY, channel.position(),length);
            }


        }

        String charListToString = charList.stream().map(e->e.toString()).collect(Collectors.joining());
        System.out.println("Mapped list: "+ charListToString);

    }

    @Override
    public void seek(int pos) throws IOException {
        bufPos = pos;
    }

    @Override
    public boolean endOfStream() {
        return endOfStream;
    }
}
