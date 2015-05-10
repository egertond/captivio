package controllers;

import model.AboutUs;
import model.ContactUsForm;
import model.Response;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import play.Play;
import play.data.Form;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application extends Controller {

    static final AboutUs aboutUs = new AboutUs();

    static {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Json.setObjectMapper(mapper);
    }

    public static Result index() {
        return ok(index.render("Home"));
    }

    public static Result aboutUs() {
        return ok(Json.toJson(aboutUs));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result contactUs() {
        Response response = new Response();

        try {
            JsonNode json = request().body().asJson();
            Form<ContactUsForm> form = Form.form(ContactUsForm.class).bind(json);

            // Invalid Request
            if (form.hasErrors()) {
                response.setError(form.errorsAsJson());
                return badRequest(Json.toJson(response));
            }

            // Valid Request
            else {
                ContactUsForm post = form.get();

                HtmlEmail email = new HtmlEmail();
                email.setHostName(Play.application().configuration().getString("smtp.host"));
                email.setSmtpPort(Play.application().configuration().getInt("smtp.port"));
                email.setSSLOnConnect(Play.application().configuration().getBoolean("smtp.ssl"));

                String username = Play.application().configuration().getString("smtp.username");
                String password = Play.application().configuration().getString("smtp.password");
                if (username != null && password != null) {
                    email.setAuthenticator(new DefaultAuthenticator(username, password));
                }

                email.setFrom(Messages.get("contactUs.sendto"));
                email.setSubject(Messages.get("contactUs.subject"));
                email.addTo(Messages.get("contactUs.sendto"));
                email.setHtmlMsg(Messages.get("contactUs.message.template", post.getName(), post.getAddress(),
                    post.getEmail(), post.getMessage()));

                email.send();

                response.setStatus(Controller.OK);
                response.setMessage(Messages.get("contactUs.success"));
                return ok(Json.toJson(response));
            }
        }
        // Invalid Request
        catch (NullPointerException | EmailException e) {
            response.setError(e.getMessage());
            return badRequest(Json.toJson(response));
        }
    }
}
