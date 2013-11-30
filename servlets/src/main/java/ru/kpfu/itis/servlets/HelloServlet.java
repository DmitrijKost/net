package ru.kpfu.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

            double d1, d2;
            int x,y,i,j;
            boolean change = false;
            Pare mass[][];
            x = new Integer(request.getParameter("x"));
            y = new Integer(request.getParameter("y"));
            if(x < y){
                x += y;
                y = x - y;
                x -= y;
                change = true;
            }
            mass = new Pare[x][y];
            for(i = 0; i < x; i++){
                for(j = 0; j < y; j++){
                    mass[i][j] = new Pare();
                }
            }
            if(x * x >= y * y * 2){
               d1 = Math.pow(Math.pow((x+3)/4,2) + Math.pow((y + 1)/2,2), 0.5);
               mass[x/4][y/2] = new Pare(1, getPower(d1));
               d2 = Math.pow(Math.pow(x - 3*(x + 1)/4, 2) + Math.pow(y - (y + 1)/2,2), 0.5);
               mass[3 * x/4][y/2] = new Pare(1, getPower(d2));
               for(i = 0; i < x; i++){
                    for(j = 0; j < y; j++){
                        if(mass[i][j].getId() != 1){
                            if(Math.pow(i - (x+3)/4,2) + Math.pow(j - (y+1)/2, 2) <= d1 * d1){
                                mass[i][j].setId(2);
                            }
                            if(Math.pow(i - 3*x/4,2) + Math.pow(j - y/2, 2) <= d2 * d2){
                                mass[i][j].setId(3);
                            }
                        }
                    }
               }
            } else{
                d1 = Math.pow(Math.pow((x+1)/2,2) + Math.pow((y+1)/2,2), 0.5);
                mass[x/2][y/2] = new Pare(1, getPower(d1));
                d2 = 0;
                mass[0][0] = new Pare(1, getPower(d2));
                for(i = 0; i < x; i++){
                    for(j = 0; j < y; j++){
                        if(mass[i][j].getId() != 1){
                            if(Math.pow(i - (x+1)/2,2) + Math.pow(j - y + (y+1)/2, 2) <= d1 * d1){
                                mass[i][j].setId(2);
                            }
                        }
                    }
                }
            }
            if(change){
                Pare newMass[][] = new Pare[y][x];
                for(i = 0; i < y; i++){
                    for(j = 0; j < x; j++){
                         newMass[i][j] = mass[j][i];
                    }
                }
                x += y;
                y = x - y;
                x -= y;
                request.setAttribute("matrix", newMass);
            }else{
                request.setAttribute("matrix", mass);
            }
            getServletConfig().getServletContext().getRequestDispatcher(
                    "/jsp/result.jsp").forward(request, response);

    }
    public static double getPower(double d){
        double p2 = Math.pow(10, -12); //P(trans)
        double k1 = 1; //K(rec):коэффициенты усиления
        double k2 = 1; //K(trans)
        double L = 1; //совокупный коэффициент затухания
        double f = 1900;//частота вещания
        double c = 299792458;//скорость света
        double l1 = c/f; //длина волны
        double g = 2.5;//коэффициент гамма

        return (p2*k1*k2*L*Math.pow(l1, 2))/16*(Math.pow(Math.PI, 2))*Math.pow(d, g);
    }

}
