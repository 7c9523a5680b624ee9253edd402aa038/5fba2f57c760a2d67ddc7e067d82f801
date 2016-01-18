package br.com.extremeantcheat.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.extremeantcheatrobot.service.ServicoPlayerOn;

@WebServlet(value = "/stop-system")
public class StopSystem extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final String KEY = "fuder-ate-talo";
	//localhost:8080/ExtremeAntCheatRobot/stop-system?key=fuder-ate-talo
	//
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String keyAcess = req.getParameter("key");
		if(keyAcess != null && !keyAcess.isEmpty()){
			if(keyAcess.equals(KEY)){
				if(ServicoPlayerOn.isLifeThred()){
					ServicoPlayerOn.SHUTDONW = false;
					resp.sendRedirect("RESPOSTA=Servico-desligado");
				}else{
					resp.sendRedirect("RESPOSTA=Nem-uma-Thread-viva-no-momento");
				}
			}
		}else{
			resp.sendRedirect("RESPOSTA=sem-key");
		}
	}
	
}
