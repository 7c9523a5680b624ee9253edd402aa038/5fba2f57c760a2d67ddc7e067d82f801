package br.com.extremeantcheat.webservice;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.extremeantcheatrobot.service.ServicoPlayerOn;

@WebServlet(value="/check-system")
public class CheckSystem extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String parametro = req.getParameter("result");
		if(parametro != null && !parametro.isEmpty()){
			if(parametro.contains("life")){
				resp.sendRedirect(ServicoPlayerOn.isLifeThred() ? "Thread-esta-viva" : "Sem-thread-no-momento..");
			}
			if(parametro.contains("exp")){
				resp.sendRedirect(ServicoPlayerOn.exeption);
			}
		}else{
			resp.sendRedirect("RESPOSTA=Ultima-data-de-execucao-foi-:-" + 
				new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(ServicoPlayerOn.ULTIMA_DATA_SERVICO));
		}
		
	}
	
}
