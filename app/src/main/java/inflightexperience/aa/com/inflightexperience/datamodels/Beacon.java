package inflightexperience.aa.com.inflightexperience.datamodels;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by user on 5/20/2017.
 */

public class Beacon {

    private String name;
    private String UUID;
    private Integer majorId;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    private Integer minorId;
    private Integer power;

    @Override
    public String toString() {
        return "Beacon{" +
                "name='" + name + '\'' +
                ", UUID='" + UUID + '\'' +
                ", majorId=" + majorId +
                ", minorId=" + minorId +
                ", power=" + power +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Beacon beacon = (Beacon) o;

        return new EqualsBuilder()
                .append(name, beacon.name)
                .append(UUID, beacon.UUID)
                .append(majorId, beacon.majorId)
                .append(minorId, beacon.minorId)
                .append(power, beacon.power)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(UUID)
                .append(majorId)
                .append(minorId)
                .append(power)
                .toHashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getMinorId() {
        return minorId;
    }

    public void setMinorId(Integer minorId) {
        this.minorId = minorId;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
