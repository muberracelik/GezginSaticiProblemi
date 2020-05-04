
public class Edge { // edge => kenar  komsuların bilgilerini tutar.

    public final Vertex hedefVertex;
    public final double mesafe;

    public Edge(Vertex hedefVertex, double mesafe) {
        this.hedefVertex = hedefVertex;
        this.mesafe = mesafe;
    }
}
