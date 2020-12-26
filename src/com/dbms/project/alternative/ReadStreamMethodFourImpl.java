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
    }

    @Override
    public void open(String path) throws FileNotFoundException {
        file = new RandomAccessFile(path,"r");
        this.channel = file.getChannel();
    }

    @Override
    public String readLn() throws IOException {
        List<Character> charList = new ArrayList<>();
        int endLine = '\n';
        if (mpbuf == null) {mpbuf = channel.map(FileChannel.MapMode.READ_ONLY, channel.position(),B);}
        Boolean keepReading = true;
        Boolean lastBuf = false;

        int newpos = 0;
        cursor =-1;
        while(keepReading) {
            while((mpbuf.hasRemaining()) && ((cursor = mpbuf.get()) != endLine)){
                char ch = (char)cursor;
                charList.add(ch);
                newpos += 1; }


            if (cursor == endLine) {
                keepReading = false;
                channel.position((int)channel.position() + newpos+1 );
            } else if (lastBuf) {
                keepReading = false;
                endOfStream = true;
                channel.close();

            } else {
                channel.position((int)channel.position() + newpos);
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
        return charListToString;

    }

    @Override
    public void seek(int pos) throws IOException {
        file.seek(pos);
        mpbuf = null;
    }

    @Override
    public boolean endOfStream() {
        return endOfStream;
    }
}
