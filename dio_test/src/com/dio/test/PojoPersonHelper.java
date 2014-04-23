package com.dio.test;

/**
 * Created by yur on 23.04.2014.
 */
public class PojoPersonHelper {
    private PojoPersonUtils2 utils;

    public PojoPersonHelper(PojoPersonUtils2 utils) {
        this.utils = utils;
    }

    public PojoPerson[] removeDupes(PojoPerson[] arr) {
        return utils.joinDistinct(arr, null);
    }


}
