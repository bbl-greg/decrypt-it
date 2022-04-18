/* (C)2022 */
package dev.gregdrake.application.businesslogic;

import dev.gregdrake.domain.runningvalues.interfaces.RunningValues;
import dev.gregdrake.infastructure.adapters.LocalDataStore;
import dev.gregdrake.infastructure.interfaces.DataStore;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateServiceTest {
  CalculateService sut;
  DataStore localDataStore;

  @BeforeEach
  void setUp() {
    localDataStore = new LocalDataStore();
    sut = new CalculateService(localDataStore);
  }

  @Test
  public void testCalculate() {
    Double expectedAverage = 2.0;
    Double expectedStdDev = 0.0;

    RunningValues actual = sut.calculate(2);

    assertEquals(expectedAverage, actual.getAverage());
    assertEquals(expectedStdDev, actual.getStdDeviation());
  }

  @Test
  public void testMultipleCalculateInvocations() {
    Double expectedSecondAverage = 5.5;
    Double expectedSecondStdDev = 1.5;

    BigDecimal expectedThirdAverage = new BigDecimal("5.666667").setScale(2, RoundingMode.CEILING);
    BigDecimal expectedThirdStdDev = new BigDecimal("1.247219").setScale(2, RoundingMode.CEILING);

    // Invocations of Calculate
    sut.calculate(4);
    RunningValues actualSecondInv = sut.calculate(7);
    RunningValues actualThirdInv = sut.calculate(6);

    BigDecimal actualThirdAverage =
        new BigDecimal(actualThirdInv.getAverage()).setScale(2, RoundingMode.CEILING);
    BigDecimal actualThirdStdDev =
        new BigDecimal(actualThirdInv.getStdDeviation()).setScale(2, RoundingMode.CEILING);

    // Actual tests
    assertEquals(expectedSecondAverage, actualSecondInv.getAverage());
    assertEquals(expectedSecondStdDev, actualSecondInv.getStdDeviation());

    assertThat(expectedThirdAverage, Matchers.comparesEqualTo(actualThirdAverage));
    assertThat(expectedThirdStdDev, Matchers.comparesEqualTo(actualThirdStdDev));
  }

  @Test
  public void testCalculateAverage() {
    Double expectedAverage = 6.25;

    Double actual = sut.calculateAverage(2, 5.5, 7.0);

    assertEquals(expectedAverage, actual);
  }

  @Test
  public void testCalculateStdDeviationCountEquals1() {
    Double expectedStdDeviation = 0.0;

    Double actual = sut.calculateStdDeviation(2, 8, 1);

    assertEquals(expectedStdDeviation, actual);
  }

  @Test
  public void testCalculateStdDeviationInvocationCountEquals2() {
    Double expectedStdDeviation = 3.0;

    Double actual = sut.calculateStdDeviation(20.0, 2.0, 2);

    assertEquals(expectedStdDeviation, actual);
  }
}
