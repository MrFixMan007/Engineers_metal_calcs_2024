package domain.model.weight

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class WeightUnitTest{
    lateinit var vmUnit: WeightUnit

    @Before
    fun init(){
        vmUnit = WeightUnit.t(10f)
    }

    @Test
    fun `must return 10000 kg`(){
        Assert.assertEquals(10000f, vmUnit.kg())
    }
    @Test
    fun `must return 10 t`(){
        Assert.assertEquals(10f, vmUnit.t())
    }
    @Test
    fun `must return 5 t`(){
        val unit = WeightUnit.kg(5000f)
        Assert.assertEquals(5f, unit.t())
    }
    @Test
    fun `must return 50 c`(){
        val unit = WeightUnit.kg(5000f)
        Assert.assertEquals(50f, unit.getUnitsOfMeasure(WeightMeasure.C))
    }
}