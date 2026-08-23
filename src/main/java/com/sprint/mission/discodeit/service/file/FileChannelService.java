package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.dto.channelDto.ChannelDto;
import com.sprint.mission.discodeit.dto.channelDto.ChannelRequest;
import com.sprint.mission.discodeit.dto.channelDto.ChannelResponse;
import com.sprint.mission.discodeit.dto.channelDto.PrivateChannelCreateRequest;
import com.sprint.mission.discodeit.dto.channelDto.PublicChannelCreateRequest;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@Service
public class FileChannelService implements ChannelService {

    private final ChannelRepository channelRepository;

    @Override
    public ChannelResponse createPrivate(PrivateChannelCreateRequest privateChannelCreateRequest) {
        return null;
    }

    @Override
    public ChannelResponse createPublic(PublicChannelCreateRequest publicChannelCreateRequest) {
        return null;
    }

    @Override
    public ChannelDto findChannel(UUID uuid) {
        return null;
    }

    @Override
    public ChannelResponse update(UUID id, ChannelRequest channelRequest) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public List<ChannelDto> findByUserId(UUID userId) {
        return List.of();
    }


}