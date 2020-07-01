package com.interview.target.gondorparking.factory;

import com.interview.target.parking.factory.ParkingSearchServiceFactory;
import com.interview.target.parking.model.ParkingType;
import com.interview.target.parking.service.ElderlyParkingService;
import com.interview.target.parking.service.PrivilegedParkingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ParkingSearchServiceFactoryTest {

    @Mock
    private ElderlyParkingService elderlyParkingService;

    @Spy
    private List<PrivilegedParkingService> parkingservices = new ArrayList<PrivilegedParkingService>();

    @InjectMocks
    private ParkingSearchServiceFactory parkingSearchServiceFactory;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        parkingservices.add(elderlyParkingService);
    }

    @Test
    public void getParkingSearchService()
    {
        when(elderlyParkingService.fit(any())).thenReturn(true);
        Assert.assertNotNull(parkingSearchServiceFactory.getSearchService(ParkingType.ELDER));
    }
}
