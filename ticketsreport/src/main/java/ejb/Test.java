package ejb;

import java.util.Date;
import java.util.ArrayList;

import entity.TicketVO;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] argc){
		CreatePDFImpl pdf = new CreatePDFImpl();
		
		ArrayList<TicketVO> ticketList = new ArrayList<TicketVO>();
		TicketVO ticket = new TicketVO();
		ticket.setFirstName("1");
		ticket.setLastName("1");
		//ticket.setDepTime(new Date());
		ticket.setStationFrom("1");
		ticket.setStationTo("1");
		ticketList.add(ticket);
		
		TicketVO ticket2 = new TicketVO();
		ticket2.setFirstName("2");
		ticket2.setLastName("2");
		//ticket2.setDepTime(new Date());
		ticket2.setStationFrom("2");
		ticket2.setStationTo("2");
		ticketList.add(ticket2);
		//pdf.create(ticketList);
		System.out.println("Ready");
	}
}
