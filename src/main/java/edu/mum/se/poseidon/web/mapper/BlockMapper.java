package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.Block;
import edu.mum.se.poseidon.web.services.dto.BlockDto;

public class BlockMapper {

	public Block getBlock(BlockDto blockDto) {
		if(blockDto == null) return null;
		Block block = new Block();
		block.setId(blockDto.getId());
		block.setEntry(blockDto.getEntry());
		block.setName(blockDto.getName());
		block.setStartDate(blockDto.getStartDate());
		block.setEndDate(blockDto.getEndDate());
		return block;
	}
	
	public BlockDto getBlockDto(Block block) {
		if(block == null) return null;
		BlockDto blockDto = new BlockDto();
		blockDto.setId(block.getId());
		blockDto.setEntry(block.getEntry());
		blockDto.setName(block.getName());
		blockDto.setStartDate(block.getStartDate());
		blockDto.setEndDate(block.getEndDate());
		return blockDto;
	}
}
