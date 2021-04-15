package proj0;

import java.sql.Array;
import java.sql.Time;

public class NBody {
    public static double readRadius(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        int planetNum = in.readInt();
        double r = in.readDouble();
        Planet[] ret = new Planet[planetNum];
        for (int i = 0; i < planetNum; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            ret[i] = new Planet(xPos, yPos, xVel, yVel, m, img);
        }
        return ret;
    }

    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.enableDoubleBuffering();

        int planetNum = new In(filename).readInt();
        double time = 0;

        for (int i = 0; i < T; i++) {
            double[] xForces = new double[planetNum];
            double[] yForces = new double[planetNum];
            for (int j = 0; j < planetNum; j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for (int j = 0; j < planetNum; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.setScale(-1 * radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }


    }
}

