package kovu.coloredlights.utils;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class BufferTools 
{
	public static float[] asFloats(Vector3f v)
	{
		return new float[]{v.x, v.y, v.z};
	}
	
    public static boolean bufferEquals(FloatBuffer bufferOne, FloatBuffer bufferTwo, int elements) 
    {
        for (int i = 0; i < elements; i++) 
        {
            if (bufferOne.get(i) != bufferTwo.get(i))
            {
                return false;
            }
        }
        return true;
    }

    public static ByteBuffer asByteBuffer(byte... values) 
    {
        ByteBuffer buffer = BufferUtils.createByteBuffer(values.length);
        buffer.put(values);
        return buffer;
    }

    public static String bufferToString(FloatBuffer buffer, int elements) 
    {
        StringBuilder bufferString = new StringBuilder();
        for (int i = 0; i < elements; i++)
        {
            bufferString.append(" ").append(buffer.get(i));
        }
        return bufferString.toString();
    }

    public static FloatBuffer asFloatBuffer(Matrix4f matrix4f) 
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        matrix4f.store(buffer);
        return buffer;
    }

    public static FloatBuffer asFlippedFloatBuffer(Matrix4f matrix4f) 
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        matrix4f.store(buffer);
        buffer.flip();
        return buffer;
    }

    public static FloatBuffer asFloatBuffer(float... values)
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        return buffer;
    }

    public static FloatBuffer reserveData(int amountOfElements) 
    {
        return BufferUtils.createFloatBuffer(amountOfElements);
    }

    public static FloatBuffer asFlippedFloatBuffer(float... values)
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
}