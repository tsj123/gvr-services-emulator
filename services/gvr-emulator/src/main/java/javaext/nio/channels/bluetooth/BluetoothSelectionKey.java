package javaext.nio.channels.bluetooth;

import android.util.Log;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class BluetoothSelectionKey extends SelectionKey {

    private SocketChannel channel;

    public BluetoothSelectionKey(SelectableChannel channel) {
        this.channel = (SocketChannel) channel;
    }

    @Override
    public void cancel() {
        Log.d("BluetoothSelectionKey", "cancel");
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SelectableChannel channel() {
        //Log.d("BluetoothSelectionKey", "channel");
        return this.channel;
    }

    @Override
    public int interestOps() {
        Log.d("BluetoothSelectionKey", "interestOps");
        return 0;
    }

    @Override
    public SelectionKey interestOps(int operations) {
        Log.d("BluetoothSelectionKey", "interestOps");
        return null;
    }

    @Override
    public boolean isValid() {
        //Log.d("BluetoothSelectionKey", "isValid");
        return this.channel.isConnected();
    }

    @Override
    public int readyOps() {
        //Log.d("BluetoothSelectionKey", "readyOps");
        if (channel.isConnected()) {
            return OP_READ;
        }
        return OP_CONNECT;
    }

    @Override
    public Selector selector() {
        Log.d("BluetoothSelectionKey", "selector");
        return null;
    }
}
