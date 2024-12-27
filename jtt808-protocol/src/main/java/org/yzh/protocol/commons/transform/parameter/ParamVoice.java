package org.yzh.protocol.commons.transform.parameter;

import io.github.yezhihao.protostar.annotation.Field;

public class ParamVoice {
    public static final Integer key = 0xF002;

    @Field(desc = "全局音量设置：0-100")
    private int voiceLevel;

    public int getVoiceLevel() {
        return voiceLevel;
    }

    public void setVoiceLevel(int voiceLevel) {
        this.voiceLevel = voiceLevel;
    }
}
