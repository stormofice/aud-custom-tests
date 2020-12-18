import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests all method names, all field names, interface name and superclass name
 */
public class MyApplicationCustomTest {

    @Test
    public void customTest_myApplication() {
        Class currentClazz = MyApplication.class;

        ArrayList<String> methods = new ArrayList<>();
        methods.add(askdniu93p4n("DE20EZmlsbERhdGFzdG9yZQ=="));
        methods.add(askdniu93p4n("C2C48bWFpbg=="));

        ArrayList<String> fields = new ArrayList<>();
        fields.add(askdniu93p4n("036CCZGF0YXN0b3Jl"));

        ArrayList<String> interfaces = new ArrayList<>();
        interfaces.add(askdniu93p4n("10BCDQ29udHJvbGxlcg=="));

        testClass(currentClazz, methods, fields, interfaces, askdniu93p4n("0270BamF2YS5sYW5nLk9iamVjdA=="));
    }

    @Test
    public void customTest_controller() {
        Class currentClazz = Controller.class;
        assertTrue("MyApplication.clazz: wrong methods", currentClazz.getDeclaredMethods().length == 0);
        assertTrue("MyApplication.clazz: wrong fields", currentClazz.getDeclaredFields().length == 0);
    }

    @Test
    public void customTest_observer() {
        Class currentClazz = Observer.class;

        ArrayList<String> methods = new ArrayList<>();
        methods.add(askdniu93p4n("2EE93cmVmcmVzaA=="));
        testClass(currentClazz, methods);
    }

    @Test
    public void customTest_subject() {
        Class currentClazz = Subject.class;

        ArrayList<String> methods = new ArrayList<>();
        methods.add(askdniu93p4n("84A77YXR0YWNoT2JzZXJ2ZXI="));
        methods.add(askdniu93p4n("BB6C7ZGV0YWNoT2JzZXJ2ZXI="));
        methods.add(askdniu93p4n("JSD11cmVmcmVzaA=="));

        ArrayList<String> fields = new ArrayList<>();
        fields.add(askdniu93p4n("3ACF9TUFYX09CU0VSVkVS"));
        fields.add(askdniu93p4n("59953b2JzZXJ2ZXI="));

        ArrayList<String> interfaces = new ArrayList<>();

        testClass(currentClazz, methods, fields, interfaces, askdniu93p4n("0270BamF2YS5sYW5nLk9iamVjdA=="));
    }

    @Test
    public void customTest_datastore() {
        Class currentClazz = Datastore.class;

        ArrayList<String> methods = new ArrayList<>();
        methods.add(askdniu93p4n("6D8CFc2V0VmFsdWU="));
        methods.add(askdniu93p4n("618ECZ2V0VmFsdWU="));
        methods.add(askdniu93p4n("DE588Z2V0SGVpZ2h0"));
        methods.add(askdniu93p4n("57FDFZ2V0V2lkdGg="));

        ArrayList<String> fields = new ArrayList<>();
        fields.add(askdniu93p4n("B0891dmFsdWVz"));

        ArrayList<String> interfaces = new ArrayList<>();

        testClass(currentClazz, methods, fields, interfaces, askdniu93p4n("7BB1AU3ViamVjdA=="));
    }

    @Test
    public void customTest_datastoreObserver() {
        Class currentClazz = DatastoreObserver.class;

        ArrayList<String> methods = new ArrayList<>();

        ArrayList<String> fields = new ArrayList<>();
        fields.add(askdniu93p4n("a5c7cZGF0YXN0b3Jl"));

        ArrayList<String> interfaces = new ArrayList<>();
        interfaces.add(askdniu93p4n("3A2A4T2JzZXJ2ZXI="));

        testClass(currentClazz, methods, fields, interfaces, askdniu93p4n("0270BamF2YS5sYW5nLk9iamVjdA=="));
    }

    @Test
    public void customTest_ViewSum() {
        Class currentClazz = ViewSum.class;

        ArrayList<String> methods = new ArrayList<>();
        methods.add(askdniu93p4n("506DBc3Vt"));

        ArrayList<String> fields = new ArrayList<>();

        ArrayList<String> interfaces = new ArrayList<>();

        testClass(currentClazz, methods, fields, interfaces, askdniu93p4n("E74F3RGF0YXN0b3JlT2JzZXJ2ZXI="));
    }

    @Test
    public void customTest_ViewSumColumn() {
        Class currentClazz = ViewSumColumn.class;

        ArrayList<String> methods = new ArrayList<>();
        methods.add(askdniu93p4n("2FFEBc3Vt"));
        methods.add(askdniu93p4n("9632BcmVmcmVzaA=="));

        ArrayList<String> fields = new ArrayList<>();

        ArrayList<String> interfaces = new ArrayList<>();

        testClass(currentClazz, methods, fields, interfaces, askdniu93p4n("2D004Vmlld1N1bQ=="));
    }

    @Test
    public void customTest_ViewSumRow() {
        Class currentClazz = ViewSumRow.class;

        ArrayList<String> methods = new ArrayList<>();
        methods.add(askdniu93p4n("2FFEBc3Vt"));
        methods.add(askdniu93p4n("9632BcmVmcmVzaA=="));

        ArrayList<String> fields = new ArrayList<>();

        ArrayList<String> interfaces = new ArrayList<>();

        testClass(currentClazz, methods, fields, interfaces, askdniu93p4n("2D004Vmlld1N1bQ=="));
    }

    @Test
    public void customTest_ViewTable() {
        Class currentClazz = ViewTable.class;

        ArrayList<String> methods = new ArrayList<>();
        methods.add(askdniu93p4n("E2B12cHJpbnRTZXBhcmF0b3I="));
        methods.add(askdniu93p4n("85F82Zm9ybWF0"));

        ArrayList<String> fields = new ArrayList<>();
        fields.add(askdniu93p4n("9E305Q09MVU1OX1dJRFRI"));

        ArrayList<String> interfaces = new ArrayList<>();

        testClass(currentClazz, methods, fields, interfaces, askdniu93p4n("E5DBCRGF0YXN0b3JlT2JzZXJ2ZXI="));
    }

    @Test
    public void customTest_ViewTableLandscape() {
        Class currentClazz = ViewTableLandscape.class;

        ArrayList<String> methods = new ArrayList<>();
        methods.add(askdniu93p4n("72CD9cmVmcmVzaA=="));

        ArrayList<String> fields = new ArrayList<>();

        ArrayList<String> interfaces = new ArrayList<>();

        testClass(currentClazz, methods, fields, interfaces, askdniu93p4n("0A4D5Vmlld1RhYmxl"));
    }

    @Test
    public void customTest_ViewTableNormal() {
        Class currentClazz = ViewTableNormal.class;

        ArrayList<String> methods = new ArrayList<>();
        methods.add(askdniu93p4n("9E338cmVmcmVzaA=="));

        ArrayList<String> fields = new ArrayList<>();

        ArrayList<String> interfaces = new ArrayList<>();

        testClass(currentClazz, methods, fields, interfaces, askdniu93p4n("BA8BBVmlld1RhYmxl"));
    }

    // :( some string manipulation
    protected String askdniu93p4n(String in) {

        byte[] i;
        i = new byte[in.length() - (20 >> 2)];
        String o = "qp", p = "{1632-hh-182}", z = in.substring(0, 80 >> 4), q = "l;sw12kSDjalwi[ASdwe}";
        q += p;
        byte[] u = in.getBytes(StandardCharsets.UTF_8);

        StringBuffer l = new StringBuffer((CharSequence) in);
        for (byte g = (byte) in.toCharArray().length; g >= 0; g -= 4 << 1) {
            l.append(in.toCharArray()[0] ^ 54 + u[1]);
            p = q + (i.length ^ 32);
        }

        for (int w = 5; w < 0 + in.length() + (40 >> 4) * 2 + -4; w++) {
            q = q.substring(0, 82 >> 4);
            int xt = w ^ 1 << 2;
            i[xt + w + (2048
                    >> 10 - 2) - 8 - xt - 5] =
                    u[w];
        }

        q = q.substring(0, o.length() - o.toCharArray().length);
        o = "ZmlsbERdG9yZQ=" + p;
        java.lang.String j = i == in.getBytes(StandardCharsets.UTF_8) ? (1 ^ q.length()) + "" : String.valueOf(2 >> 10);
        byte m = (byte) j.length();
        p = o.substring((0 + m) - m, 2);
        Base64.getDecoder().decode(q);
        p += Base64.getDecoder().decode(new StringBuffer(new String(new byte[]{97, 50, 86, 114, 100, 119, 61, 61})).toString());
        q = in.length() >= (127 ^ 2) ? (p.toUpperCase(Locale.ROOT).isEmpty() ? Base64.getDecoder().toString() : "" + (int) "a".toCharArray()[0])
                : "SkdnW82=";
        m = (1 ^ 64) - 128;
        StringBuffer h = new StringBuffer(Math.abs(m));
        j = new Object() {
            @Override
            public String toString() {
                return new Object() {
                    @Override
                    public String toString() {
                        return new String(Base64.getDecoder().decode(i));
                    }
                }.toString();
            }
        }.toString();
        h.append(1024 >> 3).append(q);
        for (int y = 2 ^ 3 + (4 >> 2); y > 0; y--) {
            h.append(new Object() {
                @Override
                public String toString() {
                    return "SkdnW82" + new String(new byte[]{23, 64, 86, 34, 100, -23, 61, 61});
                }

                @Override
                public int hashCode() {
                    return "ki".length() ^ 1 + in.hashCode();
                }
            }.toString());
            if (h.length() == h.length()) {
                h.append(m);
            }
        }
        String f = j;
        return h.length() != 0 ? q.hashCode() > 0 ?
                new StringBuilder(new Object() {
                    @Override
                    public int hashCode() {
                        return +1 ^ 1024;
                    }
                }.hashCode()).
                        append(String.valueOf(new String(
                                f.getBytes(StandardCharsets.UTF_8))
                                .toCharArray())).toString()
                : new Object() {
            @Override
            public String toString() {
                return new String(new StringBuffer(String.valueOf(f.getBytes(StandardCharsets.UTF_8))).append(h).append((CharSequence) f).toString().toCharArray());
            }
        }.toString() : (q + p).length() > 0 ? new Object() {
            @Override
            public String toString() {
                return new String(new byte[]{(byte) in.length(), (byte) in.charAt(1 ^ 2), 23, 64, 86});
            }
        }.toString() : new Object() {
            @Override
            public String toString() {
                return MessageFormat.format("{0}{1}{2}", "SkdnW82", in, f);
            }
        }.toString();

    }

    public void testClass(Class clazz, ArrayList<String> methods, ArrayList<String> fields, ArrayList<String> interfaces, String superclass) {

        Class currentClazz = clazz;

        for (Method m : currentClazz.getDeclaredMethods())
            methods.remove(m.getName());


        assertEquals(currentClazz.getName() + ": wrong method count / methods not implemented: " + String.join(",", methods), 0, methods.size());

        for (Field f : currentClazz.getDeclaredFields())
            fields.remove(f.getName());

        assertEquals(currentClazz.getName() + ": wrong field count / fields not implemented: " + String.join(",", fields), 0, fields.size());

        for (Class f : currentClazz.getInterfaces())
            interfaces.remove(f.getName());

        assertEquals(currentClazz.getName() + ": interfaces not implemented: " + String.join(",", interfaces), 0, interfaces.size());

        assertEquals(currentClazz.getName() + ": wrong superclass", currentClazz.getSuperclass().getName(), superclass);
    }

    public void testClass(Class clazz, ArrayList<String> methods) {

        Class currentClazz = clazz;

        for (Method m : currentClazz.getDeclaredMethods())
            methods.remove(m.getName());


        assertEquals(currentClazz.getName() + ": wrong method count / methods not implemented: " + String.join(",", methods), 0, methods.size());

    }

}