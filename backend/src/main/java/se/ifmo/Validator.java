package se.ifmo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
@Log4j2
public class Validator {
    private double x;
    private double y;
    private double r;

    private boolean validateR(String input) {
        try {
            double r = Double.parseDouble(input);
            setR(r);
            return r >= 1 && r <= 5 && BigDecimal.valueOf(r).scale() <= 4;
        } catch (NumberFormatException e) {
            log.error("error of r validation: {}", e.getMessage());
            return false;
        }
    }

    private boolean validateY(String input) {
        try {
            double y = Double.parseDouble(input);
            setY(y);
            return y >= -3 && y <= 5 && BigDecimal.valueOf(y).scale() <= 4;
        } catch (NumberFormatException e) {
            log.error("error of y validation: {}", e.getMessage());
            return false;
        }
    }

    private boolean validateX(String input) {
        try {
            double x = Double.parseDouble(input);
            setX(x);
            return x >= -4 && x <= 4 && BigDecimal.valueOf(x).scale() <= 4;
        } catch (NumberFormatException e) {
            log.error("error of x validation: {}", e.getMessage());
            return false;
        }
    }

    private boolean validateAll(ArrayList<String> params) {
        return validateX(params.get(0)) && validateY(params.get(1)) && validateR(params.get(2));
    }

    public boolean checkArea(ArrayList<String> params) {
        if(validateAll(params)) {
            if (getX() >= 0 && getY() >= 0) {
                return checkRectangle();
            } else if (getX() <= 0 && getY() >= 0) {
                return checkCircle();
            } else if (getX() >= 0 && getY() < 0) {
                return checkTriangle();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean checkRectangle() {
        return getX() <= getR() && getY() <= getR();
    }

    private boolean checkCircle() {
        return Math.pow(getX(), 2) + Math.pow(getY(), 2) <= Math.pow(-getR() / 2, 2);
    }

    private boolean checkTriangle() {
        return getX() <= getR() / 2 && getY() <= -getR() && -2 * getX() + getY() <= getR();
    }


}
