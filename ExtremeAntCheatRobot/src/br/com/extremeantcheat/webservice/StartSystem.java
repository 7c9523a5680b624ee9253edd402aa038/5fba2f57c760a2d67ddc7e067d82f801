package br.com.extremeantcheat.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.extremeantcheatrobot.service.ServicoPlayerOn;

@WebServlet(value = "/start-system")
public class StartSystem extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final String KEY = "tabaco-bem-massa";
	//localhost:8080/ExtremeAntCheatRobot/start-system?key=tabaco-bem-massa
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String keyAcess = req.getParameter("key");
		if(keyAcess != null && !keyAcess.isEmpty()){
			if(keyAcess.equals(KEY)){
				if(!ServicoPlayerOn.isLifeThred()){
					new ServicoPlayerOn().startService();
					resp.sendRedirect("RESPOSTA=Servico-Iniciado....");
				}else{
					resp.sendRedirect("RESPOSTA=Thread-ja-esta-em-trabalho");
				}
			}else{
				resp.sendRedirect("RESPOSTA=Key-Invalida");
			}
		}else{
			resp.sendRedirect("RESPOSTA=sem-key");
		}
	}
	
}