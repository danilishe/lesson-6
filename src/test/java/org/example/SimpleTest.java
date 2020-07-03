package org.example;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SimpleTest {
    private static final Logger log = LogManager.getLogger();
    @Test
    public void testSumm() {
        log.info("Сейчас мы будем проверять сумму");
        Assert.assertEquals("11 + 11 = 22", 22, 11 + 11);
        log.info("Тест прошёл успешно!");
    }

    @Test
    public void serializationWithSerializable() throws IOException, ClassNotFoundException {
        Human vaisilyPupkin = new Human().setAge(10)
                .setGender(Gender.MALE)
                .setName("Vaisily Pupkin");

        ByteOutputStream baos = new ByteOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(vaisilyPupkin);
        oos.flush();
        oos.close();

        log.info(vaisilyPupkin + " after serialization " + baos.toString());

        ByteInputStream bis = new ByteInputStream(baos.getBytes(), baos.getBytes().length);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Human deserializedVasiliyPupkin = (Human) ois.readObject();
        ois.close();
        bis.close();

        log.info("After deserialization: " + deserializedVasiliyPupkin);
        Assert.assertEquals(vaisilyPupkin, deserializedVasiliyPupkin);
    }

}
