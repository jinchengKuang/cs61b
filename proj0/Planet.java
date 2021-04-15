package proj0;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    final static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p2) {
        return Math.sqrt((xxPos - p2.xxPos) * (xxPos - p2.xxPos) + (yyPos - p2.yyPos) * (yyPos - p2.yyPos));
    }

    public double calcForceExertedBy(Planet p2) {
        return G * mass * p2.mass / (calcDistance(p2) * calcDistance(p2));
    }

    public double calcForceExertedByX(Planet p2) {
        return calcForceExertedBy(p2) * (p2.xxPos - xxPos) / calcDistance(p2);
    }

    public double calcForceExertedByY(Planet p2) {
        return calcForceExertedBy(p2) * (p2.yyPos - yyPos) / calcDistance(p2);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double ret = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                ret += calcForceExertedByX(p);
            }
        }
        return ret;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double ret = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                ret += calcForceExertedByY(p);
            }
        }
        return ret;
    }

    public void update(double dt, double fx, double fy) {
        double xacc = fx / mass, yacc = fy / mass;
        xxVel = xxVel + dt * xacc;
        yyVel = yyVel + dt * yacc;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
