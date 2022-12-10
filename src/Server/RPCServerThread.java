package Server;

import java.io.IOException;
import java.net.Socket;

class RPCServerThread extends Thread {
    private Socket socket;
    public RPCServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // Recebe o nome da função a ser chamada e os argumentos
            String functionName = receiveString();
            int arg1 = receiveInt();
            int arg2 = receiveInt();

            // Executa a função e envia o resultado de volta
            int result = 0;
            if (functionName.equals("add")) {
                result = arg1 + arg2;
            } else if (functionName.equals("multiply")) {
                result = arg1 * arg2;
            }
            sendInt(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Fecha o socket ao finalizar
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

// Métodos auxiliares para enviar e receber dados do socket

    private void sendInt(int value) throws IOException {
        socket.getOutputStream().write(value);
    }

    private int receiveInt() throws IOException {
        return socket.getInputStream().read();
    }

    private void sendString(String str) throws IOException {
        socket.getOutputStream().write(str.getBytes());
    }

    private String receiveString() throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = socket.getInputStream().read(buffer);
        return new String(buffer, 0, bytesRead);
    }
}