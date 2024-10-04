package com.backend.iLearn.modules.tutor.service;

import com.backend.iLearn.common.exceptions.NotFoundException;
import com.backend.iLearn.common.mapper.TutorMapper;
import com.backend.iLearn.common.utils.PaginationRequest;
import com.backend.iLearn.modules.tutor.dto.TutorDto;
import com.backend.iLearn.modules.tutor.entity.Tutor;
import com.backend.iLearn.modules.tutor.repository.TutorRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTutorService {
    private final TutorRepository tutorRepository;
    public TutorDto getOne(UUID id){
        System.out.println(id);
        var tutor = this.tutorRepository.findById(id).orElseThrow(()-> new NotFoundException("Tutor Not Found"));

        return TutorMapper.toDto(tutor);
    }

    public HashSet<TutorDto> getMany(TutorDto filter, PaginationRequest pageData){

        PageRequest pageable = PageRequest.of(pageData.getPage(), pageData.getSize(), Sort.by("createdAt").descending());
        Page<Tutor> tutors = this.tutorRepository.findAll(pageable);

        HashSet<TutorDto> tutorResponse = new HashSet<>();
        tutors.forEach((tutor -> {
            System.out.println(tutor.getReceivedChats());
            var tutorDtoData = TutorDto.builder()
                    .id(tutor.getId())
                    .createdAt(tutor.getCreatedAt())
                    .updatedAt(tutor.getUpdatedAt())
                    .build();

            tutorResponse.add(tutorDtoData);
        }));

        return tutorResponse;
    }
}
