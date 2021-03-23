package controller;

import java.util.concurrent.Semaphore;

public class ProcessoCruzamento extends Thread
{
	private Semaphore semaforo;
	private int idCarro;
	private String sentido [] = {"Norte", "Sul", "Leste", "Oeste"};
	
	public ProcessoCruzamento(int idCarro, Semaphore semaforo)
	{
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run()
	{
		try
		{
			semaforo.acquire();
			Direcao();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			semaforo.release();
		}
	}

	private void Direcao() throws InterruptedException
	{
		String destino;
		
		if(Par(idCarro))
		{
			destino = sentido[idCarro+1];
		}
		else
		{			
			destino = sentido[idCarro-1];
		}
		System.out.println("Carro #" + (idCarro+1) + " esta indo de " + sentido[idCarro] + " para " + destino + ".");
		sleep(3000);
		System.out.println("Carro #" + (idCarro+1) + " atravessou o cruzamento.");
		sleep(1500);
	}


	private boolean Par(int aux)
	{
		if (aux % 2 == 0)
		{
			return true;
		}
		return false;
	}
}