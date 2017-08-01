package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.Salon;
import java.util.List;
import io.reactivex.Observable;

/**
 * Created by framgia on 7/20/17.
 */

public interface SalonDataSource {

    Observable<List<Salon>> getAllSalons();
}
