package Client;

import java.io.IOException;
import java.net.Socket;

public class RPCClient {
    static Socket socket = new Socket();

    public static void main(String[] args) throws IOException {

        socket = new Socket("localhost", 8000);
        // Conecta-se ao servidor na porta 8000

        System.out.println("Conectado ao servidor: " + socket);
        // Envia o nome da função a ser chamada e os argumentos
        /*
        sendString("add");
        sendInt(10);
        sendInt(20);
        */
        sendString("multiply");
        sendInt(10);
        sendInt(20);

        // Recebe o resultado da chamada de procedimento remoto
        int result = receiveInt();
        System.out.println("Resultado da chamada RPC: " + result);

        // Fecha o socket ao finalizar
        socket.close();
    }

// Métodos auxiliares para enviar e receber dados do socket

    private static void sendInt(int value) throws IOException {
        socket.getOutputStream().write(value);
    }

    private static int receiveInt() throws IOException {
        return socket.getInputStream().read();
    }

    private static void sendString(String str) throws IOException {
        socket.getOutputStream().write(str.getBytes());
    }

    private static String receiveString() throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = socket.getInputStream().read(buffer);
        return new String(buffer, 0, bytesRead);
    }
}