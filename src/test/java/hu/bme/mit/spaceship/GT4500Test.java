package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTS1;
  private TorpedoStore mockTS2;

  @BeforeEach
  public void init(){
    mockTS1 = mock(TorpedoStore.class);
    mockTS2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTS1, mockTS2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result);

    verify(mockTS1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Multi3_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result);

    verify(mockTS1, times(2)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Multi5_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result);

    verify(mockTS1, times(3)).fire(1);
    verify(mockTS2, times(2)).fire(1);
  }

  @Test
  public void primaryTorpedoStore_IsEmpty_afterFiring(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS1.isEmpty()).thenReturn(true);

    // Act
    boolean result = mockTS1.isEmpty();
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void IsEmpty_noFiring(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS1.isEmpty()).thenReturn(true);

    // Act
    boolean res = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(mockTS1, times(0)).fire(1);
  }

  /*@Test
  public void IsEmpty_onlySecondaryFires(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS1.isEmpty()).thenReturn(true);

    // Act
    boolean res = ship.fireTorpedo(FiringMode.SINGLE);
    res = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }*/

  @Test
  public void IsEmpty_onlySecondaryFires(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    // Assert
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }





}
