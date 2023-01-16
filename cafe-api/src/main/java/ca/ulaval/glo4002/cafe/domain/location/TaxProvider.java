package ca.ulaval.glo4002.cafe.domain.location;

import ca.ulaval.glo4002.cafe.domain.Location;
import ca.ulaval.glo4002.cafe.domain.layout.cube.seat.customer.Tax;

public class TaxProvider {
    public Tax getTaxPercentage(Location location) {
        Tax tax = getCountryTaxPercentage(location.country());

        if (location.province().isPresent()) {
            tax = tax.add(getProvinceTaxPercentage(location.province().get()));
        }
        if (location.state().isPresent()) {
            tax = tax.add(getStateTaxPercentage(location.state().get()));
        }

        return tax;
    }

    private Tax getCountryTaxPercentage(Country country)  {
        return switch (country) {
            case CA -> new Tax(0.05f);
            case US, None -> new Tax(0);
            case CL -> new Tax(0.19f);
        };
    }

    private Tax getProvinceTaxPercentage(Province province) {
        return switch (province) {
            case AB, YT, NT, NU -> new Tax(0);
            case BC, MB -> new Tax(0.07f);
            case NB, NL, NS, PE -> new Tax(0.10f);
            case ON -> new Tax(0.08f);
            case QC -> new Tax(0.09975f);
            case SK -> new Tax(0.06f);
        };
    }

    private Tax getStateTaxPercentage(State state) {
        return switch (state) {
            case AL -> new Tax(0.04f);
            case AZ -> new Tax(0.056f);
            case CA -> new Tax(0.0725f);
            case FL -> new Tax(0.06f);
            case ME -> new Tax(0.055f);
            case NY -> new Tax(0.04f);
            case TX -> new Tax(0.0625f);
        };
    }
}
