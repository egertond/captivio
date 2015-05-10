package model;

import play.i18n.Messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AboutUs {

    Address address;

    String email;

    String name;

    String number;

    String url;

    public AboutUs() {
        address = new Address();
        email = Messages.get("aboutUs.email");
        name = Messages.get("aboutUs.name");
        number = Messages.get("aboutUs.number");
        url = Messages.get("aboutUs.url");
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return (email == null || email.isEmpty() ? null : email);
    }

    public String getName() {
        return (name == null || name.isEmpty() ? null : name);
    }

    public String getNumber() {
        return (number == null || number.isEmpty() ? null : number);
    }

    public String getUrl() {
        return (url == null || url.isEmpty() ? null : url);
    }

    public static class Address {

        String country;

        String county;

        String line1;

        String line2;

        public Address() {
            line1 = Messages.get("aboutUs.address.line1");
            line2 = Messages.get("aboutUs.address.line2");
            country = Messages.get("aboutUs.address.country");
            county = Messages.get("aboutUs.address.county");
        }

        public String getCountry() {
            return (country == null || country.isEmpty() ? null : country);
        }

        public String getCounty() {
            return (county == null || county.isEmpty() ? null : county);
        }

        public String getLine1() {
            return (line1 == null || line1.isEmpty() ? null : line1);
        }

        public String getLine2() {
            return (line2 == null || line2.isEmpty() ? null : line2);
        }

    }

}
