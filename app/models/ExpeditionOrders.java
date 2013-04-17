package models;


import play.*;
import play.mvc.*;
import play.mvc.Results.*;
import play.mvc.Results.Chunks;
import java.util.*;

import play.mvc.*;
import play.libs.*;
import play.libs.F.*;


import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import akka.actor.*;
import akka.dispatch.*;
import static akka.pattern.Patterns.ask;

import scala.concurrent.duration.*;
import akka.actor.*;
import akka.dispatch.*;


import static java.util.concurrent.TimeUnit.*;

public class ExpeditionOrders extends UntypedActor {

	static Chunks.Out<String> out;

	static ActorRef defaultActor = Akka.system().actorOf(new Props(ExpeditionOrders.class));

	static {
		Akka.system().scheduler().schedule(
			Duration.create(4, SECONDS),
			Duration.create(5, SECONDS),
			defaultActor,
			new Order(),
			Akka.system().dispatcher()
		);
	}
	
	public static void registerChunkOut(Chunks.Out<String> out) {
		ExpeditionOrders.out = out;
	}

	public void onReceive(Object message) throws Exception {
		Order order = (Order)message;
		Logger.info("Writing " + message);
		ExpeditionOrders.out.write(order.toString());
		
	}

	
}