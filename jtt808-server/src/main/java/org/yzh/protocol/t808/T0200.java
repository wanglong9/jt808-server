package org.yzh.protocol.t808;

import io.github.yezhihao.protostar.annotation.Field;
import io.github.yezhihao.protostar.annotation.Message;
import org.yzh.protocol.basics.JTMessage;
import org.yzh.protocol.commons.DateUtils;
import org.yzh.protocol.commons.JT808;
import org.yzh.protocol.commons.transform.AttributeConverter;
import org.yzh.protocol.commons.transform.AttributeConverterYue;
import org.yzh.web.model.enums.SessionKey;
import org.yzh.web.model.vo.DeviceInfo;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author yezhihao
 * https://gitee.com/yezhihao/jt808-server
 */
@Message(JT808.位置信息汇报)
public class T0200 extends JTMessage {

    @Field(length = 4, desc = "报警标志")
    private int warnBit;
    @Field(length = 4, desc = "状态")
    private int statusBit;
    @Field(length = 4, desc = "纬度")
    private int latitude;
    @Field(length = 4, desc = "经度")
    private int longitude;
    @Field(length = 2, desc = "高程(米)")
    private int altitude;
    @Field(length = 2, desc = "速度(1/10公里每小时)")
    private int speed;
    @Field(length = 2, desc = "方向")
    private int direction;
    @Field(length = 6, charset = "BCD", desc = "时间(YYMMDDHHMMSS)")
    private String dateTime;
    @Field(desc = "位置附加信息", converter = AttributeConverter.class, version = {-1, 0})
    @Field(desc = "位置附加信息(粤标)", converter = AttributeConverterYue.class, version = 1)
    private Map<Integer, Object> attributes;

    public int getWarnBit() {
        return warnBit;
    }

    public void setWarnBit(int warnBit) {
        this.warnBit = warnBit;
    }

    public int getStatusBit() {
        return statusBit;
    }

    public void setStatusBit(int statusBit) {
        this.statusBit = statusBit;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Map<Integer, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Integer, Object> attributes) {
        this.attributes = attributes;
    }

    private boolean updated;
    private String deviceId;
    private String plateNo;
    private int vehicleId;
    private double lng;
    private double lat;
    private float speedKph;
    private LocalDateTime deviceTime;

    @Override
    public boolean transform() {
        lng = getLongitude() / 1000000d;
        lat = getLatitude() / 1000000d;
        speedKph = getSpeed() / 10f;
        if (getDateTime() != null)
            deviceTime = DateUtils.parse(getDateTime());

        DeviceInfo device = SessionKey.getDeviceInfo(session);
        if (device != null) {
            deviceId = device.getDeviceId();
            plateNo = device.getPlateNo();
            vehicleId = device.getVehicleId();
        } else {
            deviceId = clientId;
            plateNo = "";
        }
        return deviceTime != null;
    }


    public boolean updated() {
        return updated ? true : !(updated = true);
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public float getSpeedKph() {
        return speedKph;
    }

    public LocalDateTime getDeviceTime() {
        return deviceTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = toStringHead();
        sb.append("T0200{dateTime=").append(dateTime);
        sb.append(",longitude=").append(longitude);
        sb.append(",latitude=").append(latitude);
        sb.append(",altitude=").append(altitude);
        sb.append(",speed=").append(speed);
        sb.append(",direction=").append(direction);
        sb.append(",warnBit=").append(Integer.toBinaryString(warnBit));
        sb.append(",statusBit=").append(Integer.toBinaryString(statusBit));
        sb.append(",attributes=").append(attributes);
        sb.append('}');
        return sb.toString();
    }
}