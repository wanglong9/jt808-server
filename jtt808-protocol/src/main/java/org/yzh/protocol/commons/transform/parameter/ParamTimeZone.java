package org.yzh.protocol.commons.transform.parameter;

import io.github.yezhihao.protostar.Schema;
import io.github.yezhihao.protostar.annotation.Field;
import io.netty.buffer.ByteBuf;

public class ParamTimeZone {

    public static final Integer key = 0xF001;

    public static final Schema<ParamTimeZone> SCHEMA = new ParamTimeZoneSchema();

    @Field(length = 1, desc = "东区/西区标志：0.东区 1.西区")
    private byte statusBit;

    @Field(desc = "偏移量:无符号位，单位为分钟，0-1440（表示0-24小时）")
    private int minuteTimeOffset;

    public byte getStatusBit() {
        return statusBit;
    }

    public void setStatusBit(byte statusBit) {
        this.statusBit = statusBit;
    }

    public int getMinuteTimeOffset() {
        return minuteTimeOffset;
    }

    public void setMinuteTimeOffset(int minuteTimeOffset) {
        this.minuteTimeOffset = minuteTimeOffset;
    }

    private static class ParamTimeZoneSchema implements Schema<ParamTimeZone> {

        private ParamTimeZoneSchema() {
        }

        @Override
        public ParamTimeZone readFrom(ByteBuf input) {
            ParamTimeZone message = new ParamTimeZone();
            message.statusBit = input.readByte();
            message.minuteTimeOffset = input.readInt();
            return message;
        }

        @Override
        public void writeTo(ByteBuf output, ParamTimeZone message) {
            output.writeByte(message.statusBit);
            output.writeInt(message.minuteTimeOffset);
        }
    }
}
