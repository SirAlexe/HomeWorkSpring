package ru.mera.samples.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import ru.mera.samples.domain.entities.AbstractNamedEntity;

@Entity
@Table( name = "IMAGES" )
@Inheritance( strategy = InheritanceType.JOINED )
public class ImageEntity extends AbstractNamedEntity {

  @Column
  private byte[] image;

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] b) {
    this.image = b;
  }

}
