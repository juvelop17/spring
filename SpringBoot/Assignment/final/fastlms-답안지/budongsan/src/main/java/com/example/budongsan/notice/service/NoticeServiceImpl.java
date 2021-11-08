package com.example.budongsan.notice.service;

import com.example.budongsan.notice.dto.NoticeDto;
import com.example.budongsan.notice.entity.Notice;
import com.example.budongsan.notice.mapper.NoticeMapper;
import com.example.budongsan.notice.model.NoticeInput;
import com.example.budongsan.notice.model.NoticeParam;
import com.example.budongsan.notice.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {
    
    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;
    
    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
        
        }
        
        return null;
    }
    
    @Override
    public boolean add(NoticeInput parameter) {

        Notice notice = Notice.builder()
                .id(parameter.getId())
                .title(parameter.getTitle())
                .contents(parameter.getContents())
                .regDt(LocalDateTime.now())
                .build();
        noticeRepository.save(notice);
        
        return true;
    }
    
    @Override
    public boolean set(NoticeInput parameter) {
        
        Optional<Notice> optionalNotice = noticeRepository.findById(parameter.getId());
        if (!optionalNotice.isPresent()) {
            //수정할 데이터가 없음
            return false;
        }
        
        Notice notice = optionalNotice.get();
        notice.setTitle(parameter.getTitle());
        notice.setContents(parameter.getContents());

        noticeRepository.save(notice);
        
        return true;
    }
    
    @Override
    public List<NoticeDto> list(NoticeParam parameter) {
        
        long totalCount = noticeMapper.selectListCount(parameter);
        
        List<NoticeDto> list = noticeMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (NoticeDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        
        return list;
    }
    
    @Override
    public NoticeDto getById(long id) {
        return noticeRepository.findById(id).map(NoticeDto::of).orElse(null);
    }
    
    @Override
    public boolean del(String idList) {
        
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }
                
                if (id > 0) {
                    noticeRepository.deleteById(id);
                }
            }
        }
        
        return true;
    }
    
    @Override
    public List<NoticeDto> frontList(NoticeParam parameter) {
        
//        if (parameter.getCategoryId() < 1) {
//            List<Notice> noticeList = noticeRepository.findAll();
//            return NoticeDto.of(noticeList);
//        }
//
//        Optional<List<Notice>> optionalNotices = noticeRepository.findByCategoryId(parameter.getCategoryId());
//        if (optionalNotices.isPresent()) {
//            return NoticeDto.of(optionalNotices.get());
//        }
        List<Notice> noticeList = noticeRepository.findAll();
        return NoticeDto.of(noticeList);
    }
    
    @Override
    public NoticeDto frontDetail(long id) {
        
        Optional<Notice> optionalNotice = noticeRepository.findById(id);
        if (optionalNotice.isPresent()) {
            return NoticeDto.of(optionalNotice.get());
        }
        return null;
    }
    
    @Override
    public List<NoticeDto> listAll() {
        
        List<Notice> noticeList = noticeRepository.findAll();
        
        return NoticeDto.of(noticeList);
    }
    
}


























