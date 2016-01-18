package br.com.extremeantcheatrobot.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.extremeantcheatrobot.dao.XitersDAO;
import br.com.extremeantcheatrobot.daoimpl.XitersDAOImpl;
import br.com.extremeantcheatrobot.entity.PlayersOn;

public class ServicoPlayerOn {

	private XitersDAO xitersDAO;
	public static Boolean SHUTDONW = Boolean.TRUE;
	private static Thread serviceCheckPlayerIsOn;
	public static Date ULTIMA_DATA_SERVICO = new Date();
	public static String exeption = "";
	
	public static void main(String[] args) {
		new ServicoPlayerOn();
	}
	
	public static boolean isLifeThred(){
		if(serviceCheckPlayerIsOn != null){
			return serviceCheckPlayerIsOn.isAlive();
		}
		return false;	
	}
	
	public void startService() {
		serviceCheckPlayerIsOn = new Thread(new Runnable() {
			@Override@SuppressWarnings("deprecation")
			public void run() {
				xitersDAO = new XitersDAOImpl();
				while(SHUTDONW){
					try{
						ULTIMA_DATA_SERVICO =  new Date();
						Thread.sleep(300000);//5min
						List<PlayersOn> findAllPlayerOn = xitersDAO.findAllPlayerOn();
						for (PlayersOn playersOn : findAllPlayerOn) {
							Date now = new Date();
							
							long minutosNow = now.getMinutes();
							long horasNow = now.getHours();
							long minutosDB = playersOn.getDataInicio().getMinutes();
							long horasDB = playersOn.getDataInicio().getHours();
							
							if(horasNow == horasDB){
								if((minutosNow - minutosDB) > 5){
									xitersDAO.deletePlayerOff(playersOn.getId());
								}
							}else{
								if(minutosNow < 5){
									minutosNow = minutosNow + 55;
									if((minutosNow - minutosDB) > 5){
										xitersDAO.deletePlayerOff(playersOn.getId());	
									}
								}else{
									xitersDAO.deletePlayerOff(playersOn.getId());
								}
							}
						}
						System.out.println("Robot exetudado as " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
					}catch(Exception e){
						xitersDAO = new XitersDAOImpl();
						exeption = e.getMessage();
						e.printStackTrace();
					}
				}
			}
		});
		serviceCheckPlayerIsOn.setName("Thread - Servico de verificacao player on");
		serviceCheckPlayerIsOn.start();
	}
}