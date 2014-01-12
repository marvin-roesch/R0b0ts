package de.mineformers.robots.util;


import net.minecraft.util.Vec3;

/**
 * GUISystem
 * <p/>
 * Vector3
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Vector3 {

    public double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 subtract(Vector3 vec) {
        return new Vector3(vec.x - x, vec.y - y, vec.z - z);
    }

    public Vector3 normalize() {
        double sqrt = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)
                + Math.pow(z, 2));
        return sqrt < 1.0E-4D ? new Vector3(0.0D, 0.0D, 0.0D) : new Vector3(
                this.x / sqrt, this.y / sqrt, this.z / sqrt);
    }

    public double dotProduct(Vector3 vec) {
        return x * vec.x + y * vec.y + z * vec.z;
    }

    public Vector3 crossProduct(Vector3 vec) {
        return new Vector3(y * vec.z - z * vec.y, z * vec.x - x * vec.z, y
                * vec.x - x * vec.y);
    }

    public Vector3 add(Vector3 vec) {
        return new Vector3(x + vec.x, y + vec.y, z + vec.z);
    }

    public double distance(Vector3 vec) {
        return Math.sqrt(this.distanceSquared(vec));
    }

    public double distanceSquared(Vector3 vec) {
        return Math.pow(x - vec.x, 2) + Math.pow(y - vec.y, 2)
                + Math.pow(z - vec.z, 2);
    }

    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public void rotate(float angle, float xAxis, float yAxis, float zAxis) {
        rotateAroundXAxis(angle * xAxis);
        rotateAroundYAxis(angle * yAxis);
        rotateAroundZAxis(angle * zAxis);
    }

    private void rotateAroundXAxis(float angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double newX = x;
        double newY = y * cos + z * sin;
        double newZ = z * cos - y * sin;

        x = newX;
        y = newY;
        z = newZ;
    }

    private void rotateAroundYAxis(float angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double newX = x * cos + z * sin;
        double newY = y;
        double newZ = z * cos - x * sin;

        x = newX;
        y = newY;
        z = newZ;
    }

    private void rotateAroundZAxis(float angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double newX = x * cos + y * sin;
        double newY = y * cos + x * sin;
        double newZ = z;

        x = newX;
        y = newY;
        z = newZ;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vector3) {
            Vector3 vec = (Vector3) obj;
            return vec.x == x && vec.y == y && vec.z == z;
        } else if(obj instanceof Vec3) {
            Vec3 vec = (Vec3) obj;
            return vec.xCoord == x && vec.yCoord == y && vec.zCoord == z;
        }
        return false;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

}
