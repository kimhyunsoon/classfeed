package semi.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semi.project.domain.BoardVo;
import semi.project.mapper.BoardMapper;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public void insertNotFileS(BoardVo boardVo) {
		// TODO Auto-generated method stub
		boardMapper.insertNotFile(boardVo);
	}

	@Override
	public void insertBoardNotFileS(BoardVo boardVo) {
		// TODO Auto-generated method stub
		boardMapper.insertBoardNotFile(boardVo);
	}

	@Override
	public List<BoardVo> selectBySucode(String sucode) {
		return boardMapper.selectBySucode(sucode);
	}

}
