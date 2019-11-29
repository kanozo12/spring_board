package com.yydh.www;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yydh.www.board.BoardDAO;
import com.yydh.www.board.BoardDTO;

@Controller
public class BoardController {

	@RequestMapping("/getBoard.do")
	public String getBoard(BoardDAO boardDAO, Model model, HttpServletRequest req) {
		model.addAttribute("board", boardDAO.getBoard(Integer.valueOf(req.getParameter("seq"))));

		return "getBoard";
	}

	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardDTO boardDTO, BoardDAO boardDAO) {
		boardDAO.insertBoard(boardDTO);
		return "getBoardList";
	}

	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardDAO boardDAO, BoardDTO boardDTO, HttpServletRequest req, Model model) {
	
		return "getBoardList";

	}
}
