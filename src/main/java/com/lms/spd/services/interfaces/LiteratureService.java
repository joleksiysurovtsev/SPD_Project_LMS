package com.lms.spd.services.interfaces;

import com.lms.spd.models.interfaces.Literature;

import java.util.List;

public interface LiteratureService {

    List<Literature> removeLiterature(int numberLit, List<Literature> lit);

    List<Literature> addLiterature(Literature litAdded, List<Literature> lit);
}
