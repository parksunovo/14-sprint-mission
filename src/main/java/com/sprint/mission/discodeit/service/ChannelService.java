package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.channelDto.ChannelDto;
import com.sprint.mission.discodeit.dto.channelDto.ChannelRequest;
import com.sprint.mission.discodeit.dto.channelDto.ChannelResponse;
import com.sprint.mission.discodeit.dto.channelDto.PrivateChannelCreateRequest;
import com.sprint.mission.discodeit.dto.channelDto.PublicChannelCreateRequest;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface ChannelService {


    ChannelResponse createPrivate(PrivateChannelCreateRequest privateChannelCreateRequest);

    ChannelResponse createPublic(PublicChannelCreateRequest publicChannelCreateRequest);

    ChannelDto findChannel(UUID uuid);

    ChannelResponse update(UUID id, ChannelRequest channelRequest);

    void delete(UUID uuid);


    List<ChannelDto> findByUserId(UUID userId);
}
