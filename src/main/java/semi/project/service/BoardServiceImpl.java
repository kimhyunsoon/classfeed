package semi.project.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import semi.project.domain.AfileVo;
import semi.project.domain.BoardVo;
import semi.project.filesetting.Path;
import semi.project.mapper.BoardMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class BoardServiceImpl implements BoardService, FileUploadService {

	private BoardMapper boardMapper;

	public BoardServiceImpl(BoardMapper boardMapper){
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardVo> selectBoardBySucode(String sucode) {
		return boardMapper.selectBoardBySucode(sucode);
	}

	@Override
	public BoardVo selectBoardBySeq(long bseq) {
		return boardMapper.selectBoardBySeq(bseq);
	}

	@Override
	public List<BoardVo> selectBoardListBySeq(long bseq) {
		List<BoardVo> boardVoList = new ArrayList<>();
		BoardVo boardVo = boardMapper.selectBoardBySeq(bseq);
		boardVoList.add(boardVo);
		return boardVoList;
	}





























	@Override
	public void insertBoardOkFileS(BoardVo boardVo) {
		// TODO Auto-generated method stub
		boardMapper.insertBoardOkFile(boardVo);
	}

	@Override
	public void insertBoardNotFileS(BoardVo boardVo) {
		// TODO Auto-generated method stub
		boardMapper.insertBoardNotFile(boardVo);
	}

	@Override
	public void deleteBySeqS(long bseq) {
		// TODO Auto-generated method stub
		boardMapper.deleteBySeq(bseq);
	}

	@Override
	public String saveStore(MultipartFile file, BoardVo boardVo) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0,idx); //파일이름 추출
		String ext = ofname.substring(idx); // 확장자명 추출
		long ms = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);
		sb.append(ext);
		String fname = sb.toString();
		long bfsize = file.getSize();
		boardVo.setBfname(fname);
		boardVo.setBfsize(bfsize);

		boolean flag = writeFile(file, fname);
		if(flag) {
			log.info("#업로드 성공");
		}else {
			log.info("#업로드 실패");
		}
		return Path.FILE_STORE + fname;

	}

	@Override
	public String sSaveStore(MultipartFile file, AfileVo afileVo) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0,idx); //파일이름 추출
		String ext = ofname.substring(idx); // 확장자명 추출
		long ms = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);
		sb.append(ext);
		String fname = sb.toString();
		long bfsize = file.getSize();
		afileVo.setAfname(fname);
		afileVo.setAfsize(bfsize);
		afileVo.setAofname(ofname);
		boolean flag = writeFile(file, fname);
		if(flag) {
			log.info("#업로드 성공");
		}else {
			log.info("#업로드 실패");
		}
		return Path.FILE_STORE + fname;
	}


	@Override
	public boolean writeFile(MultipartFile file, String fname) {
		File rDir =  new File(Path.FILE_STORE);
		if(!rDir.exists()) rDir.mkdir();
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
					fos = new FileOutputStream(Path.FILE_STORE+fname);
			fos.write(data);
			fos.flush();

			return true;
		}catch(IOException ie) {
			return false;
		}finally {
			try {
				if(fos !=null) fos.close();
			}catch(IOException ie) {}
		}
	}

	@Override
	public void insertNotFileS(BoardVo boardVo) {
		// TODO Auto-generated method stub
		boardMapper.insertNotFile(boardVo);
	}

	@Override
	public void insertOkFileS(BoardVo boardVo) {
		boardMapper.insertOkFile(boardVo);

	}

	@Override
	public List<BoardVo> boardSelectAllS(String tid, String sucode) {
		// TODO Auto-generated method stub
		List<BoardVo> list =boardMapper.boardSelectAll(tid, sucode);
		return list;
	}

	@Override
	public List<BoardVo> boardSelectClassS(String sucode) {
		// TODO Auto-generated method stub
		List<BoardVo> list = boardMapper.boardSelectClass(sucode);
		return list;
	}


	@Override
	public List<BoardVo> selectBySucode(String sucode) {
		return boardMapper.selectBySucode(sucode);
	}

	@Override
	public String boardSelectTidS(long bseq) {
		log.info("service bseq1"+bseq);
		return boardMapper.boardSelectTid(bseq);
	}

	@Override
	public List<BoardVo> boardSelectBySeqS(long bseq) {
		log.info("service bseq2"+bseq);
		return boardMapper.boardSelectBySeq(bseq);
	}

	@Override
	public BoardVo getBoardS(long bseq) {
		// TODO Auto-generated method stub
		return boardMapper.getBoard(bseq);
	}

	@Override
	public void boardUpdateS(BoardVo boardVo) {
		// TODO Auto-generated method stub
		boardMapper.boardUpdate(boardVo);
	}




}
