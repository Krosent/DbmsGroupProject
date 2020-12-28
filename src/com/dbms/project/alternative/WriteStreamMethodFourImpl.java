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

    //A file channel is created that is connected to the new output file, creating a random-access file with a read and write mode
    @Override
    public void create(String name) throws IOException {
        file = new RandomAccessFile(name,"rw");
        this.channel = file.getChannel();
    }

    //write is performed by adding B characters of the string you want to write in a buffer, representing the shared memory space
    //Whenever this buffer is full, these characters are unmapped to the file and the buffer is reinitialized so it can again take B characters
    @Override
    public void writeLn(String data) throws IOException {
        if (mpbuf == null) {mpbuf = channel.map(FileChannel.MapMode.READ_WRITE, writePos,B);
            mpbuf.clear();
            mpbuf.asCharBuffer();}
        String data2 = data + '\n';
        char[] chars =  data2.toCharArray();
        int i;
        for(i=0; i<chars.length;i++) {
            //unmap the characters in the buffer to the output file and reinitialize the buffer so it can again take characters
            if (!mpbuf.hasRemaining()){
                writePos += mpbuf.capacity();
                mpbuf = channel.map(FileChannel.MapMode.READ_WRITE, writePos,B);
            }
            mpbuf.put((byte)chars[i]);
        }

    }

    //no more 'writeln' operations can be done on the stream, and therefore the created file through this stream
    //and the corresponding resources associated with the stream are released
    @Override
    public void close() throws IOException {
        channel.close();
    }
}
