package edu.mum.se.poseidon.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.web.models.Block;
import edu.mum.se.poseidon.web.services.dto.BlockDto;

@Component
public class BlockMapper {

	private EntryMapper entryMapper;
	
	@Autowired
	public BlockMapper(EntryMapper entryMapper) {
		this.entryMapper = entryMapper;
	}
	
	public Block getBlock(BlockDto blockDto) {
		if(blockDto == null) return null;
		Block block = new Block();
		block.setId(blockDto.getId());
		block.setEntryModel(entryMapper.getEntryModelFrom(blockDto.getEntryDto()));
		block.setName(blockDto.getName());
		block.setStartDate(blockDto.getStartDate());
		block.setEndDate(blockDto.getEndDate());
		return block;
	}
	
	public BlockDto getBlockDto(Block block) {
		if(block == null) return null;
		BlockDto blockDto = new BlockDto();
		blockDto.setId(block.getId());
		blockDto.setEntryDto(entryMapper.getEntryDtoFrom(block.getEntryModel()));
		blockDto.setName(block.getName());
		blockDto.setStartDate(block.getStartDate());
		blockDto.setEndDate(block.getEndDate());
		return blockDto;
	}
}
