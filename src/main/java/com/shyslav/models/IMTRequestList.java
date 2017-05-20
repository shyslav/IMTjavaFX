package com.shyslav.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Shyshkin Vladyslav on 20.05.17.
 */
public class IMTRequestList extends ArrayList<IMTRequest> {
    private HashMap<Integer, IMTRequest> map = new HashMap<>();

    @Override
    public boolean add(IMTRequest imtRequest) {
        map.put(imtRequest.getDishID(), imtRequest);
        return super.add(imtRequest);
    }

    public IMTRequest getByDishID(int dishID) {
        return map.get(dishID);
    }
}
