package com.imsavva;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * To run this example, navigate to the module's folder and run mvn jetty:run command.
 * The servlet will be available at http://localhost:8080/asynch-servlet
 *
 * @author Savva Kodeikin
 */
@WebServlet(urlPatterns = {"/async-servlet"}, asyncSupported = true)
public class AsynchronousServlet extends HttpServlet {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final AsyncContext context = req.startAsync();
        final PrintWriter writer = resp.getWriter();

        executorService.execute(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writer.write("Async response after a long I/O work\n");
            context.complete();
        });
    }
}
