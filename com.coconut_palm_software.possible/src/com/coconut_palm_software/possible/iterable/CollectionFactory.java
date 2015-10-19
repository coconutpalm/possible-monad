package com.coconut_palm_software.possible.iterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

public class CollectionFactory {
    public static <T> LinkedList<T> linkedList(T...ts) {
        LinkedList<T> result = new LinkedList<T>();
        for (T t : ts) {
            result.add(t);
        }
        return result;
    }

    public static <T> ArrayList<T> arrayList(T...ts) {
        ArrayList<T> result = new ArrayList<T>(ts.length);
        for (T t : ts) {
            result.add(t);
        }
        return result;
    }

    public static <T> List<T> list(T...ts) {
        return arrayList(ts);
    }

    public static final class KV<K,V> {
        K k;
        V v;
        public KV(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    public static <K,V> KV<K,V> kv(K k, V v) {
        return new KV<K,V>(k, v);
    }

    public static <K,V> LinkedHashMap<K, V> linkedHashMap(KV<K,V>...kvs) {
        LinkedHashMap<K,V> result = new LinkedHashMap<K,V>();
        for (KV<K, V> kv : kvs) {
            result.put(kv.k, kv.v);
        }
        return result;
    }

    public static <K, V> HashMap<K,V> hashMap(KV<K,V>... kvs) {
        HashMap<K,V> result = new HashMap<K,V>();
        for (KV<K, V> kv : kvs) {
            result.put(kv.k, kv.v);
        }
        return result;
    }

    public static <K, V> TreeMap<K,V> treeMap(KV<K,V>... kvs) {
        TreeMap<K,V> result = new TreeMap<K,V>();
        for (KV<K, V> kv : kvs) {
            result.put(kv.k, kv.v);
        }
        return result;
    }

    public static <K,V> Map<K,V> map(KV<K,V>...kvs) {
        return hashMap(kvs);
    }

    public static <T> HashSet<T> hashSet(T...ts) {
        HashSet<T> result = new HashSet<T>();
        for (T t : ts) {
            result.add(t);
        }
        return result;
    }

    public static <T> TreeSet<T> treeSet(T...ts) {
        TreeSet<T> result = new TreeSet<T>();
        for (T t : ts) {
            result.add(t);
        }
        return result;
    }

    public static <T> Set<T> set(T...ts) {
        return hashSet(ts);
    }
}
