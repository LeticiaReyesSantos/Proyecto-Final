package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.print.attribute.standard.Severity;

public class Servidor extends Thread {

	private ServerSocket servidor;
	private int puerto;
	public boolean estado = false;

	public Servidor(int puerto) {
		this.puerto = puerto;
	}

	public void iniciar() {
		estado = true;
		this.start();
	}

	public void detener() {
		estado= false;
		try {
			if(servidor != null)
				servidor.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {

		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Se ha inicializado el servidor en el puerto: "+ puerto);


			while(estado) {
				try {
					Socket sc = servidor.accept();
					new Thread(()-> manejarRespaldos(sc)).start();
					
				}catch(IOException e) {
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void manejarRespaldos(Socket sc) {
		try {
			DataInputStream in = new DataInputStream(sc.getInputStream());
			
			
			File backup = new File("Backup");
			if(!backup.exists())
				backup.mkdir();
			
			
			File archivo = genererarArchivoRespaldo(backup);


			try (DataOutputStream ou = new DataOutputStream(new FileOutputStream(archivo))){;
			int unByte;

			while((unByte = in.read()) != -1) {
				ou.write(unByte);
			}

			ou.flush();
			System.out.println("Archivo respaldado: " + archivo.getName());
			}catch(IOException e){

			}
			
			DataOutputStream outCliente = new DataOutputStream(sc.getOutputStream());
			outCliente.writeUTF(archivo.getName());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private File genererarArchivoRespaldo(File carpeta) {
		int cont = 1;
		File archivo;

		do {
			archivo = new File(carpeta, "clinica-Respaldo-"+ cont+ ".dat");
			cont++;
		}while(archivo.exists());

		return archivo;
	}

	public static void main(String[] args) {

	}

}
