package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    public static void main(String[] args) throws IOException {
// Cria um novo socket de servidor na porta 8000
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Servidor iniciado na porta 8000");
        // Fica em loop para aceitar novas conexões
        while (true) {
            // Aceita uma nova conexão
            Socket socket = serverSocket.accept();
            System.out.println("Nova conexão recebida: " + socket);

            // Cria um novo thread para lidar com a conexão
            new RPCServerThread(socket).start();
        }
    }
}