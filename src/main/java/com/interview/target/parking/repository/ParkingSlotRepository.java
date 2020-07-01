package com.interview.target.parking.repository;

import com.interview.target.parking.model.ParkingSlot;
import com.interview.target.parking.model.ParkingSlotId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer> {

    @Query("select ps from ParkingSlot ps,ParkingLane pl where pl.parkingSystemId=:iparkingsystemId and pl.buildingId =:ibuildingId and pl.laneType=2 and ps.parkingSystemId=pl.parkingSystemId and ps.buildingId=pl.buildingId and ps.floorId=pl.floorId and ps.laneId =pl.laneId")
    List<ParkingSlot> fetchParkingSlotsByBuilding(@Param("iparkingsystemId") int parkingsystemId, @Param("ibuildingId") int buildingId);

    @Query("select ps from ParkingSlot ps,ParkingLane pl where pl.parkingSystemId=:iparkingsystemId and pl.laneType=2 and ps.parkingSystemId=pl.parkingSystemId and ps.buildingId=pl.buildingId and ps.floorId=pl.floorId and ps.laneId =pl.laneId")
    List<ParkingSlot> fetchParkingSlots(@Param("iparkingsystemId") int parkingsystemId);

    @Query("select ps from ParkingSlot ps where  ps.parkingSystemId=:iparkingsystemId and ps.buildingId=:ibuildingId and ps.floorId=:ifloorId and ps.laneId =:ilaneId and ps.slotId = :islotId")
    ParkingSlot fetchParkingSlotBySlotId(@Param("iparkingsystemId") int parkingsystemId, @Param("ibuildingId") int buildingId, @Param("ifloorId") int floorId, @Param("ilaneId") int laneId,@Param("islotId") int slotId);

    @Query("select ps from ParkingSlot ps,ParkingLane pl where pl.parkingSystemId=:iparkingsystemId and pl.laneType=2 and ps.vehicleType=:svehicleType and ps.parkingSystemId=pl.parkingSystemId and ps.buildingId=pl.buildingId and ps.floorId=pl.floorId and ps.laneId =pl.laneId")
    List<ParkingSlot> fetchParkingSlotByVehicleType(@Param("iparkingsystemId") int parkingsystemId, @Param("svehicleType") String vehicleType);
}
