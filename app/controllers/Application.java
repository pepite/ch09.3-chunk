package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Results.*;
import play.mvc.Results.Chunks;

import views.html.*;

import models.*;
import java.util.*;

public class Application extends Controller {
  

    public static Result index() {
        return ok(index.render());
    }

	public static Result liveUpdate() {
  		// Prepare a chunked text stream
		Chunks<String> chunks = new StringChunks() {

    		// Called when the stream is ready
			public void onReady(Chunks.Out<String> out) { 
				char[] buffer = new char[1024 * 5];
        		Arrays.fill(buffer, ' ');
        		out.write(new String(buffer));
				ExpeditionOrders.registerChunkOut(out);
			}

		};
  		response().setContentType("text/html;charset=UTF-8");
		return ok(chunks);
	}

}
