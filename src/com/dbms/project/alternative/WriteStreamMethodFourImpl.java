package com.dbms.project.alternative;

import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class WriteStreamMethodFourImpl implements WriteStreamInterface {

    RandomAccessFile file;
    FileChannel channel;
    int B;
    MappedByteBuffer mpbuf;
    int writePos;

    public WriteStreamMethodFourImpl(int B) {
        this.file = null;
        this.channel = null;
        this.B = B;
        this.mpbuf = null;
        this.writePos = 0;
    }

    @Override
    public void create(String name) throws IOException {
        file = new RandomAccessFile(name,"rw");
        this.channel = file.getChannel();
    }

    @Override
    public void writeLn(String data) throws IOException {
        if (mpbuf == null) {mpbuf = channel.map(FileChannel.MapMode.READ_WRITE, writePos,B);
        mpbuf.clear();
        mpbuf.asCharBuffer();}
        String data2 = data + '\n';
        char[] chars =  data2.toCharArray();
        int i;
        for(i=0; i<chars.length;i++) {
            if (!mpbuf.hasRemaining()){
                writePos += mpbuf.capacity();
                mpbuf = channel.map(FileChannel.MapMode.READ_WRITE, writePos,B);
            }
            mpbuf.put((byte)chars[i]);
            }

    }

    @Override
    public void close() throws IOException {
        channel.close();
    }
}
