import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ResizingHashMapCustomTest {

    @Test(timeout = 100)
    public void customTest_constructor() {

        try {
            new ResizingHashMap<String, Integer>(-1);
            Assert.fail("Constructor: Missing exception");
        } catch (Exception e) {
            Assert.assertSame("Constructor: wrong exception thrown", IllegalArgumentException.class, e.getClass());
        }

        try {
            new ResizingHashMap<String, Integer>(0);
            Assert.fail("Constructor: Missing exception");
        } catch (Exception e) {
            Assert.assertSame("Constructor: wrong exception thrown", IllegalArgumentException.class, e.getClass());
        }

        try {
            new ResizingHashMap<String, Integer>(Integer.MAX_VALUE);
        } catch (Error e) {
            Assert.assertSame("Constructor: wrong error created", OutOfMemoryError.class, e.getClass());
        }

        ResizingHashMap<String, Integer> sizeTest;

        for (int i = 0; i < 512; i++) {
            int number = ThreadLocalRandom.current().nextInt(1, 32768);
            sizeTest = new ResizingHashMap<>(number);
            Assert.assertEquals("Constructor: wrong arrays size", number, sizeTest.buckets.length);
            Assert.assertEquals("Constructor: wrong map size", 0, sizeTest.size);
            Assert.assertEquals("Constructor: wrong map size", 0, sizeTest.size());
        }

    }

    @Test(timeout = 100)
    public void customTest_getBucketIndex() {
        for (int i = 0; i < 2048; i++) {
            int capacity = ThreadLocalRandom.current().nextInt(1, 4096);
            ResizingHashMap<String, Integer> test = new ResizingHashMap<>(capacity);
            String key = getRandomString(512);
            int hashCode = key.hashCode();
            if (hashCode < 0)
                hashCode = -hashCode;
            Assert.assertEquals("getBucketIndex: bucket index differed", hashCode % capacity, test.getBucketIndex(key));
        }

        try {
            new ResizingHashMap<>(1).getBucketIndex(null);
            Assert.fail("getBucketIndex: exception expected");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

    }

    @Test(timeout = 100)
    public void customTest_insertMapping() {
        createInsertMap(false);
    }

    @Test(timeout = 100)
    public void customTest_getMapping() {

        ResizingHashMap<String, Integer> sampleTestMap = new ResizingHashMap<>(16);

        ArrayList<Mapping<String, Integer>> mappings = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            int bucketEntrySize = ThreadLocalRandom.current().nextInt(2, 256);
            for (int k = 0; k < bucketEntrySize; k++) {
                Mapping<String, Integer> temporaryMapping = new Mapping<>(getRandomString(16), ThreadLocalRandom.current().nextInt(16), null);
                mappings.add(temporaryMapping);
                sampleTestMap.insertMapping(sampleTestMap.getBucketIndex(temporaryMapping.key), temporaryMapping);
            }
        }

        for (Mapping<String, Integer> mapping : mappings) {
            Assert.assertEquals("getMapping: mapping not found in map", mapping, sampleTestMap.getMapping(mapping.key));
        }

        try {
            sampleTestMap.getMapping(null);
            Assert.fail("getMapping: exception expected, key was null");
        } catch (Exception e) {
            Assert.assertSame("getMapping: wrong exception thrown", IllegalArgumentException.class, e.getClass());
        }

        try {
            Mapping<String, Integer> result = sampleTestMap.getMapping("sample.key");
            Assert.assertNull("getMapping: getMapping(keyThatIsNotInMap) should be null", result);
        } catch (Exception e) {
            Assert.fail("getMapping: exception should not be thrown (key was not in map)");
        }

    }

    @Test(timeout = 100)
    public void customTest_get() {
        createInsertMap(true);
    }

    @Test(timeout = 200)
    public void customTest_containsKey() {
        ResizingHashMap<String, Integer> sampleTestMap = new ResizingHashMap<>(64);
        ArrayList<Mapping<String, Integer>> mappings = createSampleTestMap(sampleTestMap);

        for (Mapping<String, Integer> m : mappings) {
            Assert.assertTrue("containsKey: key should have been in map", sampleTestMap.containsKey(m.key));
        }

        for (int i = 0; i < 256; i++) {
            Assert.assertFalse("containsKey: key should not have been in map", sampleTestMap.containsKey(getRandomString(16)));
        }

        try {
            sampleTestMap.containsKey(null);
            Assert.fail("containsKey: containsKey(null) should result in an exception");
        } catch (Exception e) {
            Assert.assertSame("containsKey: wrong exception thrown", IllegalArgumentException.class, e.getClass());
        }

    }

    @Test(timeout = 50)
    public void customTest_put() {

        ResizingHashMap<String, Integer> lifesad = new ResizingHashMap<>(128);

        HashMap<String, Integer> expected = new HashMap<>();
        for (int i = 0; i < 2048; i++) {
            String rand = getRandomString(512);
            int number = ThreadLocalRandom.current().nextInt(256);
            expected.put(rand, number);
            Assert.assertTrue("put: key was not in map", lifesad.put(rand, number));
            Assert.assertEquals("put: size did not increase", i + 1, lifesad.size());
        }

        for (String s : expected.keySet()) {
            Assert.assertEquals("put: value was not in map", expected.get(s), lifesad.get(s));
            Assert.assertTrue("put: value was not in map", lifesad.containsKey(s));
            Assert.assertFalse("put: key was already in map", lifesad.put(s, expected.get(s)));

            expected.replace(s, ThreadLocalRandom.current().nextInt(4096));
            Assert.assertFalse("put: key was already in map, only update", lifesad.put(s, expected.get(s)));
            Assert.assertEquals("put: key was not properly updated", expected.get(s), lifesad.get(s));
        }


        try {
            lifesad.put(null, 1);
            Assert.fail("put: key was null");
        } catch (Exception e) {
            Assert.assertSame("put: wrong exeption thrown", IllegalArgumentException.class, e.getClass());
        }

    }

    @Test(timeout = 50)
    public void customTest_remove() {
        ResizingHashMap<String, Integer> liveverysad = new ResizingHashMap<>(128);
        for (int i = 0; i < 2048; i++) {
            String rand = getRandomString(512);
            int number = ThreadLocalRandom.current().nextInt(256);
            liveverysad.put(rand, number);
            Assert.assertTrue("remove: key should have been removed", liveverysad.remove(rand));
            Assert.assertEquals("remove: size should stay at zero", 0, liveverysad.size);
        }

        for (int i = 0; i < 512; i++) {
            Assert.assertFalse("remove: key should have been removed", liveverysad.remove(getRandomString(16)));
        }

        try {
            liveverysad.remove(null);
            Assert.fail("remove: exception expected");
        } catch (Exception e) {
            Assert.assertSame("remove: wrong exception thrown", IllegalArgumentException.class, e.getClass());
        }

    }

    @Test(timeout = 50)
    public void customTest_resize() {

        ResizingHashMap<String, Integer> sample = new ResizingHashMap<>(10);

        try {
            sample.resize(5);
            Assert.fail("resize: exception expected");
        } catch (Exception e) {
            Assert.assertSame("resize: wrong exception thrown", IllegalArgumentException.class, e.getClass());
        }

        try {
            sample.resize(-1);
            Assert.fail("resize: exception expected");
        } catch (Exception e) {
            Assert.assertSame("resize: wrong exception thrown", IllegalArgumentException.class, e.getClass());
        }

        ArrayList<Mapping<String, Integer>> mappings = createSampleTestMap(sample);

        for (int i = 0; i < 64; i++) {
            int newSize = ThreadLocalRandom.current().nextInt(0, 2048);

            if (newSize <= 0 || newSize < sample.buckets.length) {
                try {
                    sample.resize(newSize);
                    Assert.fail("resize: exception expected");
                } catch (Exception e) {
                    Assert.assertSame("resize: wrong exception thrown", IllegalArgumentException.class, e.getClass());
                }
            } else {
                sample.resize(newSize);
                Assert.assertEquals("resize: wrong new size", newSize, sample.buckets.length);

                for (Mapping<String, Integer> m : mappings) {
                    Assert.assertTrue("resize: key was lost", sample.containsKey(m.key));
                    Assert.assertEquals("resize: value was changed", m.value, sample.get(m.key));
                }
            }
        }


    }

    private void createInsertMap(boolean checkGet) {
        ResizingHashMap<String, Integer> sampleTestMap = new ResizingHashMap<>(16);

        HashMap<Integer, ArrayList<Mapping<String, Integer>>> mappedBucketExpectations = new HashMap<>();

        int count = 0;

        for (int i = 0; i < 16; i++) {
            ArrayList<Mapping<String, Integer>> expectedBucketEntries = new ArrayList<>();

            int bucketEntrySize = ThreadLocalRandom.current().nextInt(2, 256);

            for (int k = 0; k < bucketEntrySize; k++) {
                Mapping<String, Integer> temporaryMapping = new Mapping<>(getRandomString(16), ThreadLocalRandom.current().nextInt(16), null);
                expectedBucketEntries.add(temporaryMapping);
                count++;
                if (checkGet) {
                    sampleTestMap.put(temporaryMapping.key, temporaryMapping.value);
                } else {
                    sampleTestMap.insertMapping(i, temporaryMapping);
                }
                Assert.assertEquals("size wrong: ", count, sampleTestMap.size());
            }

            mappedBucketExpectations.put(i, expectedBucketEntries);
        }

        if (checkGet) {
            for (int i : mappedBucketExpectations.keySet()) {
                for (Mapping<String, Integer> m : mappedBucketExpectations.get(i)) {
                    Assert.assertEquals("get: value was different", m.value, sampleTestMap.get(m.key));
                }
            }

            try {
                sampleTestMap.get(null);
                Assert.fail("get: exception expected when key is null");
            } catch (Exception e) {
                Assert.assertSame("get: wrong exception", IllegalArgumentException.class, e.getClass());
            }

        } else {
            for (int i : mappedBucketExpectations.keySet()) {
                Mapping<String, Integer> cur = sampleTestMap.buckets[i];
                for (int k = 0; k < mappedBucketExpectations.get(i).size(); k++) {
                    Assert.assertEquals("insertMapping: mapping not in map or at wrong position", mappedBucketExpectations.get(i).remove(0), cur);
                    cur = cur.next;
                }
            }

            try {
                sampleTestMap.insertMapping(-1, new Mapping<>("", 0, null));
                Assert.fail("insertMapping: exception expected, index was negative");
            } catch (Exception e) {
                Assert.assertSame("insertMapping: wrong type of exception thrown", IllegalArgumentException.class, e.getClass());
            }

            try {
                sampleTestMap.insertMapping(16, new Mapping<>("", 0, null));
                Assert.fail("insertMapping: exception expected, index was bigger than array length");
            } catch (Exception e) {
                Assert.assertSame("insertMapping: wrong type of exception thrown", IllegalArgumentException.class, e.getClass());
            }
        }

    }

    private ArrayList<Mapping<String, Integer>> createSampleTestMap(ResizingHashMap<String, Integer> in) {
        ArrayList<Mapping<String, Integer>> mappings = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            int bucketEntrySize = ThreadLocalRandom.current().nextInt(2, 256);
            for (int k = 0; k < bucketEntrySize; k++) {
                Mapping<String, Integer> temporaryMapping = new Mapping<>(getRandomString(16), ThreadLocalRandom.current().nextInt(16), null);
                mappings.add(temporaryMapping);
                in.put(temporaryMapping.key, temporaryMapping.value);
            }
        }

        return mappings;
    }

    private String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char z = (char) ('z' - (char) ThreadLocalRandom.current().nextInt(52));
            sb.append(z);
        }
        return sb.toString();
    }

}