package domain.model.volume

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

//import org.junit.jupiter.api.Assertions.*

class VolumeUnitTest{

    lateinit var vmUnit: VolumeUnit

    @Before
    fun init(){
        vmUnit = VolumeUnit.m3(10f)
    }

    @Test
    fun `must return 10 m`(){
        assertEquals(10f, vmUnit.m3())
    }
    @Test
    fun `must return 10000000 cm`(){
        assertEquals(10000000f, vmUnit.cm3())
    }
    @Test
    fun `must return 0,01 m`(){
        val unit = VolumeUnit.dm3(10f)
        assertEquals(0.01f, unit.m3())
    }
    @Test
    fun `must return 0,1 m`(){
        val unit = VolumeUnit.dm3(100f)
        assertEquals(0.1f, unit.getUnitsOfMeasure(VolumeMeasure.M3))
    }
}